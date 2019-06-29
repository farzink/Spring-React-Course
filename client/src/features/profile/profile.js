import React, { Component } from 'react'
import { withRouter } from "react-router-dom";
import { connect } from "react-redux";
import { getProfile, changeVisibility } from '../../data/actions/person-actions';




export class Profile extends Component {

    constructor(props) {
        super(props)
    }

    componentDidMount() {
        const { isFetched, getProfile } = this.props
        if (!isFetched) {
            getProfile()
        }
    }
    render() {
        const { profile, changeVisibility } = this.props
        return (
            <div className="course-detail">
                <div>
                    <button style={{ marginBottom: '16px', padding: '16px' }} onClick={() => {
                        this.props.history.push('/')
                    }}>
                        <i style={{ margin: '0 8px' }} className="fas fa-arrow-left"></i>
                        <span>Back to Dashboard</span>
                    </button>
                </div>
                {(profile) ? <div className="box">
                    <div className="row">
                        <span className="title">Personal Details</span>
                    </div>
                    <div className="row">
                        <span className="text">firstname:</span>
                        <span className="text">{profile.firstname}</span>
                    </div>
                    <div className="row">
                        <span className="text">lastname:</span>
                        <span className="text">{profile.lastname}</span>
                    </div>
                    <div className="row">
                        <span className="text">birthday:</span>
                        <span className="text">{profile.birthday}</span>
                    </div>


                    <div className="row">
                        <span className="text">Is Profile Hidden:</span>
                        <button style={{
                            backgroundColor: profile.hidden? "#ce9898": "#66cc66",
                            border: 0,
                            width: "40px",
                            borderRadius: "4px"
                        }} onClick={() => {
                            changeVisibility(profile.hidden)
                        }}>{profile.hidden ? "Yes": "No"}</button>                        
                    </div>

                    {/* <div className="row">
                        <span className="text">Enrolled Courses:</span>
                        <span className="text">{profile.enrolledCourses ? profile.enrolledCourses.map(c => <div>{c}</div>) : "Nothing Yet"}</span>
                    </div> */}
                    <div className="row">
                        <span className="text">Achieved Certificates:</span>
                        <span className="text">{profile.achievedCertificates.length > 0 ? profile.achievedCertificates.map((c, index) => <div key={index}>{c}</div>) : "Nothing Yet"}</span>
                    </div>
                    <div className="row">
                        <span className="text">Visited Courses:</span>
                        <span className="text">{profile.visitedCourses.length > 0 ? profile.visitedCourses.map((c, index) => <div key={index}>{c}</div>) : "Nothing Yet"}</span>
                    </div>



                </div> : <div>is Loading</div>
                }

            </div>
        )
    }
}





const state = (state, props) => {
    return {
        profile: state.profile.data,
        isFetched: state.profile.isFetched
    }
}

const actions = {
    getProfile: getProfile,
    changeVisibility: changeVisibility
}

export default withRouter(connect(state, actions)(Profile))


