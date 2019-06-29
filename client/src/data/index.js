import { createStore, combineReducers, applyMiddleware, compose } from 'redux'
import thunk from 'redux-thunk'

import { courseReducer } from './reducers/course-reducer';
import { certificateReducer } from './reducers/certificates-reducer';
import { personReducer } from './reducers/persons-reducer';


const enhancer = compose(applyMiddleware(thunk), window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__())
const allReducers = combineReducers({
    courses: courseReducer,
    certificates: certificateReducer,
    profile: personReducer

})

export const store = createStore(allReducers, {}, enhancer)
