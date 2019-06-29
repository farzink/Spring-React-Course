import * as endpoints from '../endpoints'
import axios from 'axios';

export const GET_COURSES_ACTION = "GET_COURSES_ACTION";
export const REQUEST_BEGIN = "REQUEST_BEGIN";
export const REQUEST_END = "REQUEST_END";





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



export const getCourses = (extra = null) => {
    return ds => {
        ds(beginRequest());
        endpoints.prepareHeaders()
        axios.get(endpoints.createUrlFor(endpoints.GET_COURSES)).then(success => {
            ds({
                type: GET_COURSES_ACTION,
                payload: success.data
            })
            ds(endRequest());
            if(extra) extra()
        }).catch(error => {
            ds(endRequest());
        })
    }
}


