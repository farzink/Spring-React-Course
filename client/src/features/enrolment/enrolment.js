import React, { Component } from 'react'
import { withRouter } from "react-router-dom";
import { connect } from "react-redux";
import HWizard from '../horizontal-wizard/horozontal-wizard';
import { enroll } from '../../data/actions/person-actions';


export class Enrolment extends Component {

    getCertificateById = id => {
        const { certificates } = this.props
        return certificates.find(c => c.id === id)
    }
    getCourseById = id => {
        const { courses } = this.props
        return courses.find(c => c.id === id)
    }

    prepareCertificates = certificates => {
        const { profile } = this.props
        return certificates.map(certificate => {
            return {
                name: this.getCertificateById(certificate).name,
                pass: profile.data.achievedCertificates.includes(certificate)
            }
        })
    }

    prepareCourses = courses => {
        const { profile } = this.props
        return courses.map(course => {
            return {
                name: this.getCourseById(course).name,
                pass: profile.enrolledCourses.find(c => c.course.id === course && c.progress === 100) != null ? true : false
            }
        })
    }

    evaluateCertificates = certificates => {
        const { profile } = this.props
        if (certificates.length === 0) {
            return true
        }
        for (let certificate of certificates) {
            if (!profile.data.achievedCertificates.includes(certificate))
                return false
        }
        return true
    }

    evaluateCourses = courses => {
        const { profile } = this.props
        if (courses.length === 0) {
            return true
        }
        for (let course of courses) {
            if (profile.enrolledCourses.find(c => c.course.id === course && c.progress === 100) == null)
                return false
        }
        return true
    }

    render() {
        const id = this.props.match.params.id
        const course = this.props.courses.find(c => c.id === +id);
        console.log(course)
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
                {(course) ? <div className="box">
                    <div className="row">
                        <span className="title">{course.name}</span>
                    </div>
                    <div className="row">
                        <span className="text">Description:</span>
                        <span className="text">{course.description}</span>
                    </div>
                    <div className="row">
                        <span className="text">Duration:</span>
                        <span className="text">{course.durationInMinutes}</span>
                    </div>
                    <div className="row">
                        <span className="text">Since:</span>
                        <span className="text">{course.since}</span>
                    </div>
                    <div className="row">
                        <span className="text">Price:</span>
                        <span className="text">{course.priceInEuro} Euro</span>
                    </div>
                    <div className="row">
                        <span className="text">Certificate:</span>
                        <span className="text">{course.certificate ? course.certificate : 'N/A'}</span>
                    </div>
                    <div style={{ border: 'thin solid #eee', marginTop: '24px' }}>
                        <div className="row">
                            <span className="text">Prerequisite Certificates:</span>
                            {
                                course.prerequisiteCertificates ?
                                    course.prerequisiteCertificates.length === 0 ?
                                        "N/A" :
                                        <HWizard data={this.prepareCertificates(course.prerequisiteCertificates)} /> :
                                    "N/A"
                            }
                        </div>
                        <div className="row">
                            <span className="text">Prerequisite Courses:</span>
                            {
                                course.prerequisiteCourses ?
                                    course.prerequisiteCourses.length === 0 ?
                                        "N/A" :
                                        <HWizard data={this.prepareCourses(course.prerequisiteCourses)} /> :
                                    "N/A"
                            }


                        </div>
                    </div>

                    <div style={{ border: 'thin solid #eee', marginTop: '24px', padding: '24px 12px' }}>
                        <button style={{ margin: '0 8px' }} onClick={() => this.props.enroll(course.id, () => {
                            this.props.history.push("/")
                        })}
                            title={!this.evaluateCertificates(course.prerequisiteCertificates) || !this.evaluateCourses(course.prerequisiteCourses) ?
                                "you cant enroll as the prerequisite(s) are not satisfied" :
                                "to entoll"}
                            disabled={!this.evaluateCertificates(course.prerequisiteCertificates) || !this.evaluateCourses(course.prerequisiteCourses)}
                        >Enroll</button>
                    </div>


                    <img className="img" src='images/education.png'></img>

                </div> : <div>is Loading</div>
                }


                {/* certificate: null
description: "Learning letters from A to Z"
durationInMinutes: 60
id: 39
name: "Letters"
prerequisiteCertificates: []
prerequisiteCourses: []
priceInEuro: 4
since: "2018-09-12"
tags: (3) ["LANGUAGE", "BASIC", "USK6"] */}

            </div>
        )
    }
}

const stp = (state, props) => {
    return {
        courses: state.courses.data,
        certificates: state.certificates.data,
        profile: state.profile
    }
}

const atp = {
    enroll: enroll
}

export default withRouter(connect(stp, atp)(Enrolment))
