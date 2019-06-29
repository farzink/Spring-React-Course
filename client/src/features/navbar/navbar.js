import React from 'react'


function Navbar({ router }) {
    
    return (
        <nav className="navbar">
            <div className="left"></div>
            <div className="right">
                <div className="logout">
                    <button style={{ margin: '0 12px' }} title="profile" onClick={() => {
                        router.push("/profile")
                    }}>
                        <i style={{
                            color: "goldenrod"
                        }} className="fas fa-user"></i>
                    </button>
                    <button style={{ margin: '0 12px' }} title="Logout" onClick={() => {
                        localStorage.removeItem('token')
                        router.push("/login")
                    }}>
                        <i className="fas fa-sign-out-alt"></i>
                    </button>
                </div>
            </div>
        </nav>
    )
}


export default Navbar



