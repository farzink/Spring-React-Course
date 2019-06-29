import React from "react";
import { Redirect } from "react-router-dom";

const AuthRoute = (props) => {
    const token = localStorage.getItem('token')
    return token === null ? <Redirect to='/login' /> : props.children
}


export default AuthRoute
