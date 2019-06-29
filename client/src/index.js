import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import * as serviceWorker from './serviceWorker';
import { store } from './data';
import { Provider } from 'react-redux'
import { HashRouter } from "react-router-dom";


import { getCourses } from './data/actions/course-actions';
import { getEnrolledCourses } from './data/actions/person-actions';
import { getCertificates } from './data/actions/certificate-actions';
import RouterConfig from './router-config';

const init = () => {
    store.dispatch(getCertificates())
}

init()



const root = <Provider store={store}>
    <HashRouter>
        <RouterConfig />
    </HashRouter>
</Provider>

ReactDOM.render(root, document.getElementById('root'));

serviceWorker.unregister();
