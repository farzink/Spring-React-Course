import React from 'react'

import { Route, Switch } from "react-router-dom";

// import Home from "./components/home";
// import OrdersView from "./components/orders-view";
// import CustomersView from "./components/customers-view";
// import Login from './components/auth/login';
// import AuthRoute from './components/auth/authRoute';
// import Navbar from './components/navbar/navbar';
// import NavLayout from './components/layout/nav-layout';
import Login from './features/login/login';
import AuthRoute from './features/login/authRoute';
import NavLayout from './layouts/nav-layout';
import Dashboard from "./features/dashboard/dashboard";
import Register from './features/login/register';
import Enrolment from './features/enrolment/enrolment';
import Profile from './features/profile/profile';
import CourseDetail from './features/course-detail/course-detail';




const RouterConfig = () => {
    return (
        <Switch>
            <Route path="/login" component={Login} />
            <Route path="/register" component={Register} />
            <Route path="/" exact>
                <AuthRoute>                    
                    <NavLayout component={Dashboard} />
                </AuthRoute>
            </Route>
            <Route path="/enrolment/:id" exact>
                <AuthRoute>                    
                    <NavLayout component={Enrolment} />
                </AuthRoute>
            </Route>
            <Route path="/detail/:id" exact>
                <AuthRoute>                    
                    <NavLayout component={CourseDetail} />
                </AuthRoute>
            </Route>
            <Route path="/profile" exact>
                <AuthRoute>                    
                    <NavLayout component={Profile} />
                </AuthRoute>
            </Route>
            {/* <Route path="/orders">
                <AuthRoute >
                    <NavLayout component={OrdersView} />
                </AuthRoute>
            </Route>
            <AuthRoute path="/customers">
                <NavLayout component={CustomersView} />
            </AuthRoute> */}
        </Switch>

    )

}

export default RouterConfig
