import {
    REQUEST_BEGIN,
    REQUEST_END,
    GET_ENROLLED_COURSES_ACTION,
    ENROLL_COURSE_ACTION,
    GET_PROFILE,
    GET_VISIBILITY_ACTION,
    GET_MAKE_PROGRESS_ACTION,
    GET_ENROLL_ACTION,
    GET_TAKE_EXAM_ACTION
} from '../actions/person-actions';
import { stat } from 'fs';



export const personReducer = (state = {
    data: {
        id: 0,
        firstname: '',
        lastname: '',
        birthday: '',
        achievedCertificates: [],
        visitedCourses: [],
        hidden: false
    },
    enrolledCourses: [],
    requestStatus: false,
    isFetched: false,
    isEnrolledFetched: false,
}, action) => {
    switch (action.type) {
        case REQUEST_BEGIN:
            return Object.assign({}, state, { requestStatus: true })
        case REQUEST_END:
            return Object.assign({}, state, { requestStatus: false })
        case GET_PROFILE:
            return Object.assign({}, state, {
                data: action.payload.data,
                isFetched: true
            })
        case GET_VISIBILITY_ACTION:
            return Object.assign({}, state, {
                data: action.payload.data,
                isFetched: true
            })
        case GET_ENROLLED_COURSES_ACTION:
            return Object.assign({}, state, {

                enrolledCourses: action.payload,
                isEnrolledFetched: true,
            }
            )
        case GET_ENROLL_ACTION:
            return Object.assign({}, state, {
                enrolledCourses: [
                    ...state.enrolledCourses, {
                        course: action.payload.course
                    }
                ],
                isEnrolledFetched: true,
            }
            )
        case GET_MAKE_PROGRESS_ACTION:
            return Object.assign({}, state, {
                enrolledCourses: state.enrolledCourses.map(c => {
                    if (c.course.id === action.payload.course.id) {
                        c.progress = action.payload.progress
                    }
                    return c
                }),
                isEnrolledFetched: true,
            }
            )
        case GET_TAKE_EXAM_ACTION:
            // return Object.assign({}, state, {
            //     enrolledCourses: state.enrolledCourses.map(c => {
            if (action.payload.data === null) {
                return Object.assign({}, state, {
                    enrolledCourses: state.enrolledCourses.map(c => {
                        if (c.course.id === action.payload.id) {
                            c.progress = 0
                        }
                        return c
                    })
                }
                )
            } else
                return Object.assign({}, state, {
                    data: {
                        ...state.data,
                        achievedCertificates: action.payload.data.achievedCertificates
                    }
                })

        case ENROLL_COURSE_ACTION:
            return Object.assign({}, state, {
                data: {
                    ...state.data,
                    enrolledCourses: [...state.data.enrolledCourses, {
                        personId: state.data.id,
                        courseId: action.payload.courseId,
                        progress: 50
                    }]
                },
                isFetched: true
            }
            )
        default: return state;
    }
}