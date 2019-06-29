import React, { Component } from 'react'
import {
    withRouter, 
    NavLink
} from 'react-router-dom'
import './login.css'
import { createUrlFor, POST_LOGIN } from '../../data/endpoints'
import axios from 'axios';


class Login extends Component {
    constructor(props) {
        super(props)
        this.state = {
            email: "",
            password: "",
            requestStatus: false,
            errorMessage: ''
        }
    }

    login = () => {
        const { email, password } = this.state
        const { history } = this.props
        axios.post(createUrlFor(POST_LOGIN), {
            email,
            password
        }).then(result => {
            localStorage.removeItem('token')
            localStorage.setItem('token', result.data.data.accessToken)
            history.push("/")
        }).catch(error => {
            this.setState({
                errorMessage: error.response.status === 401 ? 'Wrong username and / or password' : 'Server error, Please contact the administrators'
            })
        }).finally(data => {
            this.setState({
                requestStatus: false
            })
        })
    }
    render() {
        const { email, password, requestStatus, errorMessage } = this.state
        // const { history } = this.props
        return (
            <div className="container">
                <div className="auth_form">
                    <div className="auth_form--container">
                        <div className="auth_form--image">
                            <img src="./images/auth-img.png" alt="" />
                        </div>
                        <div className="auth_form--inputs">
                            <div className="title">
                                Member Login
                        </div>
                            <div className="form_inputs">
                                <div className="input">
                                    <input type="text" placeholder="Email" onChange={e => {
                                        this.setState({
                                            email: e.target.value
                                        })
                                    }} />
                                    <i className="fa fa fa-envelope"></i>
                                </div>
                                <div className="input">
                                    <input type="password" placeholder="Password" onChange={e => {
                                        this.setState({
                                            password: e.target.value
                                        })
                                    }} />
                                    <i className="fa fa fa-lock"></i>
                                </div>
                            </div>
                            <div className="form_actions">
                                <button className="primary_button" onClick={e => {
                                    e.preventDefault()
                                    if (email !== "" && password !== "") {
                                        this.setState({
                                            requestStatus: true
                                        }, () => {
                                            this.login()
                                        })
                                    }
                                }
                                } disabled={(email !== "" && password !== "" ? false : true) && (requestStatus ? false : true)}
                                    title={email !== "" && password !== "" ? "" : "please fill both fields"}>{
                                        requestStatus ?
                                            "please wait" :
                                            "Login"
                                    }</button>
                                <button className="link_button"></button>
                                <div>{errorMessage}</div>
                            </div>
                            <div className="form_link">
                                <NavLink className="link_button" to="/register">
                                    not a member? Register here
                                </NavLink>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}


export default withRouter(Login)
