import React, { Component } from 'react'
import {
    withRouter,
    NavLink
} from 'react-router-dom'
import './login.css'
import { createUrlFor, POST_REGISTER } from '../../data/endpoints'
import axios from 'axios';


class Register extends Component {
    constructor(props) {
        super(props)
        this.state = {
            email: "",
            password: "",
            firstname: "",
            lastname: '',
            birthday: '',
            requestStatus: false,
            errorMessage: ''
        }
    }

    register = () => {
        const { email, password, lastname, firstname, birthday } = this.state
        const { history } = this.props
        axios.post(createUrlFor(POST_REGISTER), {
            email,
            password,
            firstname,
            lastname,
            birthday
        }).then(result => {            
            history.push("/login")
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
                                Member Registration
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
                                <div className="input">
                                    <input type="text" placeholder="First Name" onChange={e => {
                                        this.setState({
                                            firstname: e.target.value
                                        })
                                    }} />
                                    <i className="fa fa fa-lock"></i>
                                </div>
                                <div className="input">
                                    <input type="text" placeholder="Last Name" onChange={e => {
                                        this.setState({
                                            lastname: e.target.value
                                        })
                                    }} />
                                    <i className="fa fa fa-lock"></i>
                                </div>
                                <div className="input">
                                    <input type="text" placeholder="Date of Birth" onChange={e => {
                                        this.setState({
                                            birthday: e.target.value
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
                                            this.register()
                                            //history.push("/")

                                        })
                                    }
                                }
                                } disabled={(email !== "" && password !== "" ? false : true) && (requestStatus ? false : true)}
                                    title={email !== "" && password !== "" ? "" : "please fill both fields"}>{
                                        requestStatus ?
                                            "please wait" :
                                            "Register"
                                    }</button>
                                <button className="link_button"></button>
                                <div>{errorMessage}</div>
                            </div>
                            <div className="form_link">
                                <NavLink className="link_button" to="/login">
                                    already Registered? Login here
                                </NavLink>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}


export default withRouter(Register)
