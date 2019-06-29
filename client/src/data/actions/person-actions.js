import * as endpoints from '../endpoints'
import { prepareHeaders } from '../endpoints';
import axios from 'axios';

export const GET_PROFILE = "GET_PROFILE";
export const REQUEST_BEGIN = "REQUEST_BEGIN";
export const REQUEST_END = "REQUEST_END";
export const GET_ENROLLED_COURSES_ACTION = "GET_ENROLLED_COURSES_ACTION";
export const ENROLL_COURSE_ACTION = "ENROLL_COURSE_ACTION";
export const GET_VISIBILITY_ACTION = "GET_VISIBILITY_ACTION";
export const GET_MAKE_PROGRESS_ACTION = "GET_MAKE_PROGRESS_ACTION";
export const GET_ENROLL_ACTION = "GET_ENROLL_ACTION";
export const GET_TAKE_EXAM_ACTION = "GET_TAKE_EXAM_ACTION";




const beginRequest = () => {
    return {
        type: REQUEST_BEGIN
    }
}

const endRequest = () => {
    return {
        type: REQUEST_END
    }
}

export const getProfile = (extra = null) => {
    return ds => {
        ds(beginRequest());
        endpoints.prepareHeaders()
        axios.get(endpoints.createUrlFor(endpoints.GET_PROFILE)).then(success => {
            ds({
                type: GET_PROFILE,
                payload: success.data
            })
            ds(endRequest());
        }).catch(error => {
            ds(endRequest());
        })
    }
}


export const changeVisibility = (visibility, extra = null) => {
    return ds => {
        ds(beginRequest());
        endpoints.prepareHeaders()
        axios.get(endpoints.createUrlFor(`${endpoints.GET_AUTH_VISIBILITY}/${visibility}`)).then(success => {
            ds({
                type: GET_VISIBILITY_ACTION,
                payload: success.data
            })
            ds(endRequest());
        }).catch(error => {
            ds(endRequest());
        })
    }
}





export const getEnrolledCourses = (extra = null) => {
    return ds => {
        ds(beginRequest());
        prepareHeaders()

        axios.get(endpoints.createUrlFor(endpoints.GET_ENROLLED_COURSES)).then(success => {
            ds({
                type: GET_ENROLLED_COURSES_ACTION,
                payload: success.data.data
            })
            ds(endRequest());
        }).catch(error => {
            ds(endRequest());
        })
    }
}
export const makeProgress = (model, extra = null) => {
    return ds => {
        ds(beginRequest());
        prepareHeaders()
        axios.get(endpoints.createUrlFor(`${endpoints.GET_MAKE_PROGRESS}/${model}/progress`)).then(success => {
            ds({
                type: GET_MAKE_PROGRESS_ACTION,
                payload: success.data.data
            })
            ds(endRequest());
        }).catch(error => {
            ds(endRequest());
        })
    }
}

export const enroll = (model, extra = null) => {
    return ds => {
        ds(beginRequest());
        prepareHeaders()

        axios.get(endpoints.createUrlFor(`${endpoints.GET_COURSES}/${model}/enroll`)).then(success => {
            ds({
                type: GET_ENROLL_ACTION,
                payload: success.data.data
            })
            ds(endRequest());
            if (extra) {
                extra()
            }
        }).catch(error => {
            ds(endRequest());
        })
    }
}


export const leave = (model, extra = null) => {
    return ds => {
        ds(beginRequest());
        prepareHeaders()

        axios.get(endpoints.createUrlFor(`${endpoints.GET_ENROLLED_COURSES}/${model}/leave`)).then(success => {
            getEnrolledCourses()
            ds(endRequest());
            if (extra) {
                extra()
            }
        }).catch(error => {
            ds(endRequest());
        })
    }
}

export const courseVisit = (model, extra = null) => {
    return ds => {
        ds(beginRequest())
        prepareHeaders()
        axios.get(endpoints.createUrlFor(`${endpoints.GET_COURSES}/${model}/visit`)).then(success => {
            getProfile()
            ds(endRequest());
            if (extra) {
                extra()
            }
        }).catch(error => {
            ds(endRequest());
        })
    }
}

export const enrollCourse = (model, extra = null) => {
    return ds => {
        ds(beginRequest());
        // endpoints.prpareHeaders()
        // axios.get(endpoints.createUrlFor(endpoints.GET_ENROLLED_COURSES)).then(success => {
        //     ds({
        //         type: GET_ENROLLED_COURSES_ACTION,
        //         payload: success.data
        //     })
        //     ds(endRequest());
        // }).catch(error => {
        //     ds(endRequest());
        // })
        ds({
            type: ENROLL_COURSE_ACTION,
            payload: model
        })
        ds(endRequest());
    }
}

export const takeExam = (model, extra = null) => {
    return ds => {
        ds(beginRequest());
        endpoints.prepareHeaders()

        axios.get(endpoints.createUrlFor(`${endpoints.GET_TAKE_EXAM}/${model}`)).then(success => {
            ds({
                type: GET_TAKE_EXAM_ACTION,
                payload: {id: model, data: success.data.data}
            })
            if (extra) {
                if(success.data.data !== null) {
                    extra(true)
                } else {
                    extra(false)
                }
            }
            ds(endRequest());
        }).catch(error => {
            ds(endRequest());
        })
    }
}


