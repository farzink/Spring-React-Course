import React from 'react'
import styles from './sidebar-style'
import { connect } from "react-redux";

function Sidebar({ profile, courses }) {
    return (
        <aside style={styles.sidebar}>
            <div>
                <section style={styles.container}>
                    <header>Head-Hunting</header>
                    <div>CSV</div>
                    <div>JSON</div>
                </section>
                <section style={styles.container}>
                    <header>Queries</header>
                    <div>Which Courses</div>
                    <div>Which Courses</div>
                    <div>Which Courses</div>
                    <div>For the longest</div>
                </section>
                <section style={styles.container}>
                    <header>Recently Visited</header>
                    {       
                        courses.length !== 0 && 
                            profile.visitedCourses.map((course, index) => {
                            
                            return <div key={index}>
                                {getCourseById(courses, course).name}
                            </div>
                        })
                    }
                </section>
            </div>
        </aside>
    )
}


const state = (state, props) => {
    return {
        profile: state.profile.data,
        courses: state.courses.data
    }
}

const actions = {
}

const getCourseById = (courses, id) => {
    return courses.find(course => course.id === id)
}

export default connect(state, actions)(Sidebar)
