import React, { Component } from 'react'
import { withRouter } from 'react-router-dom'
import { connect } from "react-redux";
import Enrolled from './components/enrolled/enrolled';
import Achieved from './components/achieved/achieved';
import Available from './components/available/available';
import { enrollCourse, getEnrolledCourses, courseVisit, getProfile } from '../../data/actions/person-actions';
import { getCourses } from '../../data/actions/course-actions';
import Certificate from './components/certificate/certificate';

class Dashboard extends Component {

    constructor(props) {
        super(props)
        this.state = {
            examMode: false,
            examCourseId: 0
        }
    }

    componentDidMount() {
        const { isEnrolledFetched, getEnrolledCourses, isFetched, getCourses, getProfile } = this.props
        //if (!isFetched) {
        getProfile()
        getCourses(() => {
            //if (!isEnrolledFetched) {
            getEnrolledCourses()
            //}
        })
        //}
    }

    getAchievedCertificates() {        
        const {profile, certificates} = this.props
        if(profile && certificates) {
            return profile.data.achievedCertificates.map(c => {
                return certificates.find(f => f.id === c)
            })
        }
    }

    getAvailableCourses = (courses, enrolled) => {
        return courses.filter(c => {
            return !enrolled.map(e => e.course.id).includes(c.id)
        })
    }

    // enroll = (courseId) => {
    //     this.props.enrollCourse({ courseId: courseId })
    // }

    takeExam = (courseId) => {
        this.setState({
            examMode: true,
            examCourseId: courseId
        })
    }

    leaveExam = () => {
        this.setState({
            examMode: false,
            examCourseId: 0
        })
    }

    render() {
        const { courses, enrolled, history, visit, getProfile } = this.props
        const { examMode, examCourseId } = this.state
        return (
            <>

                <div className="row">
                    <section className="container-section"><div style={{ background: '#78909c' }} className="header">Available</div>
                        <Available data={{ available: this.getAvailableCourses(courses, enrolled), history: history, visit: visit, getProfile: getProfile }} />
                    </section>
                    <section className="container-section"><div style={{ background: '#26a69a' }} className="header">Enrolled</div>
                        <Enrolled data={{ enrolled: enrolled, history: history }} />
                    </section>

                </div>

                <div className="row">
                    <section className="container-section"><div style={{ background: '#8d6e63' }} className="header">Achieved</div>
                        <Achieved data={{ achieved: enrolled.filter(c => c.progress === 100) }} />
                    </section>
                    <section className="container-section"><div style={{ background: '#bf360c' }} className="header">Certifications</div>
                        <Certificate data={{ certificates: this.getAchievedCertificates()}} />
                    </section>
                </div>


                {
                    <div className="modal" style={{ display: examMode ? 'block' : 'none', position: 'fixed', height: '100vh', width: '100vw', top: 0, left: 0, background: 'rgba(61,61,61, 0.5)' }}>
                        <div className="close" onClick={() => this.leaveExam()}>X</div>
                        <div className="overlay" style={{}}></div>
                        <div className="content" style={{}}>
                            hello
                    </div>
                    </div>}

            </>
        )
    }
}

const stp = (state, props) => {
    return {
        requestStatus: state.courses.requestStatus,
        courses: state.courses.data,
        enrolled: state.profile.enrolledCourses,
        isEnrolledFetched: state.profile.isEnrolledFetched,
        isFetched: state.courses.isFetched,
        certificates: state.certificates.data,
        profile: state.profile
    }
}

const atp = {
    enrollCourse: enrollCourse,
    getEnrolledCourses: getEnrolledCourses,
    getCourses: getCourses,
    visit: courseVisit,
    getProfile: getProfile
}

export default withRouter(connect(stp, atp)(Dashboard))
