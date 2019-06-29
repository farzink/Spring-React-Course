import { REQUEST_BEGIN, REQUEST_END, GET_COURSES_ACTION } from "../actions/course-actions";



export const courseReducer = (state = {
    data: [],    
    requestStatus: false,
    isFetched: false    
}, action) => {
    switch (action.type) {
        case REQUEST_BEGIN:
            return Object.assign({}, state, { requestStatus: true })
        case REQUEST_END:
            return Object.assign({}, state, { requestStatus: false })

        case GET_COURSES_ACTION:
            return Object.assign({}, state, {
                data: action.payload.data.map(p => {
                    return p
                }),
                isFetched: true                
            })              
            
        default: return state;
    }
}