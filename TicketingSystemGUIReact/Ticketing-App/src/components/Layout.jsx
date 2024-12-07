import React from 'react'
import '../styles/Layout.css'
import Form from './Form.jsx'

const Layout = () => {
  return (
    <div className='main-container'>
        <div className="sub-container-left">
            <Form />
        </div>
        <div className="sub-container-right">
            <div className="sub-container-right-up">
                <h1>Right Up</h1>
            </div>
            <div className="sub-container-right-down">
                <h1>Right Down</h1>
            </div>
        </div>
    </div>
  )
}

export default Layout