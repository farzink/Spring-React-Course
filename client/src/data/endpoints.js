import axios from 'axios'
const base = "/api/"



export const GET_COURSES = "courses";
export const GET_PROFILE = "/auth/me";
export const GET_ENROLLED_COURSES = "courses/enrolled";
export const GET_CERTIFICATES = "certificates";
export const GET_AUTH_VISIBILITY = "auth/visibility";
export const GET_MAKE_PROGRESS = "courses/enrolled";
export const GET_TAKE_EXAM = "certificates/exam";

export const POST_LOGIN = "auth/token";
export const POST_REGISTER = "auth/register";






export const prepareHeaders = () => {
    axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem('token')}`
    axios.defaults.headers.post['Content-Type'] = 'application/json'
}

export const createUrlFor = (url) => {
    if(url.startsWith('fpi')) 
        return `${url}`
    return `${base}${url}`
}
