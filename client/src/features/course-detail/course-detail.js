import React, { Component } from 'react'
import { withRouter } from "react-router-dom";
import { connect } from "react-redux";
import { Line, Circle } from 'rc-progress';
import { makeProgress, leave, takeExam } from '../../data/actions/person-actions';


export class CourseDetail extends Component {
    render() {
        const id = this.props.match.params.id
        const course = this.props.enrolled.find(c => c.course.id === +id);
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
                        <span className="title">{course.course.name}</span>
                    </div>
                    <div className="row">
                        <span style={{ fontWeight: 700 }} className="text">Progress:</span>
                        <span style={{ fontWeight: 700 }} className="text">{course.progress}%</span>
                    </div>
                    <div className="row">
                        <span className="text">Description:</span>
                        <span className="text">{course.course.description}</span>
                    </div>
                    <div className="row">
                        <span className="text">Duration:</span>
                        <span className="text">{course.course.durationInMinutes}</span>
                    </div>
                    <div className="row">
                        <span className="text">Since:</span>
                        <span className="text">{course.course.since}</span>
                    </div>
                    <div className="row">
                        <span className="text">Price:</span>
                        <span className="text">{course.course.priceInEuro} Euro</span>
                    </div>
                    <div className="row">
                        <span className="text">Certificate:</span>
                        <span className="text">{course.course.certificate ? course.course.certificate : 'N/A'}</span>
                    </div>
                    <div className="img" style={{ height: '150px', width: '150px', background: 'white', top: '90px' }}>
                        <Circle percent={course.progress} strokeWidth="5" strokeColor={progressColorGenerator(course.progress)} trailColor="#eee" />
                    </div>

                    <div style={{ border: 'thin solid #eee', marginTop: '24px', padding: '24px 12px' }}>
                        <button style={{ margin: '0 8px' }} onClick={() => this.props.makeProgress(course.course.id)}
                            title={course.progress === 100 ? "no more progress" : "to make progress"}
                            disabled={course.progress === 100 ? true : false}>Make Progress</button>
                        <button style={{ margin: '0 8px' }}
                            title={course.course.certificate == null ? "this course has no exam" : "to take exam"}
                            disabled={course.course.certificate == null ? true : false} onClick={() => {
                                this.props.takeExam(course.course.id, (status) => {
                                    if(status) {
                                        this.props.history.push("/")
                                    } else {
                                        alert('You dont have enough progress to take the exam')
                                    }
                                })
                            }}>Take Exam</button>

                        <button style={{ margin: '0 8px' }}
                            title={course.progress === 100 ? "you cant leave the course as it is completed" : "to leave the course"}
                            disabled={course.progress === 100 ? true : false}
                            onClick={() => this.props.leave(course.course.id, () => {
                                this.props.history.push("/")
                            }
                            )}>Leave</button>
                    </div>

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
        enrolled: state.profile.enrolledCourses
    }
}

const atp = {
    makeProgress: makeProgress,
    leave: leave,
    takeExam: takeExam
}


function progressColorGenerator(progress) {
    return `
        rgb(100,${progress * 2.5 + 5} ,23)
    `
}

export default withRouter(connect(stp, atp)(CourseDetail))
