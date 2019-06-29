import React from 'react'

function HWizard({data}) {

    // const mock = [
    //     {name: 'Math', pass: true},
    //     {name: 'Math 2', pass: false},
    //     {name: 'Math 3', pass: false},
    //     {name: 'Math 4', pass: false},
    // ]

    return (
        <div className="h-wizard">
            {
                data.map((m, index) => {
                   return <div key={index} className="node">
                        <div style={{fontSize: '14px'}}>{m.name}</div>
                        <span>{m.pass ? <i style={{color: '#1faa00'}} className="fas fa-check"></i> : <i style={{color: '#d50000'}} className="fas fa-times"></i>}</span>
                    </div>
                })
            }
        </div>
    )
}

export default HWizard
