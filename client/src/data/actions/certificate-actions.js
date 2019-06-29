import * as endpoints from '../endpoints'
import axios from 'axios';

export const GET_CERTIFICATES_ACTION = "GET_CERTIFICATES_ACTION";
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

export const getCertificates = (extra = null) => {
    return ds => {
        ds(beginRequest());
        // endpoints.prpareHeaders()
        axios.get(endpoints.createUrlFor(endpoints.GET_CERTIFICATES)).then(success => {
            ds({
                type: GET_CERTIFICATES_ACTION,
                payload: success.data
            })
            ds(endRequest());
        }).catch(error => {
            ds(endRequest());
        })
    }
}