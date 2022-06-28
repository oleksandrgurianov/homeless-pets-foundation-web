import React from 'react'
import ReactDOM from 'react-dom'
import App from './App'
import {BrowserRouter, Route, Routes} from 'react-router-dom'
import {AuthProvider} from './context/AuthProvider'

ReactDOM.render(
    <AuthProvider>
        <BrowserRouter>
            <Routes>
                <Route path='/*' element={<App />} />
            </Routes>
        </BrowserRouter>
    </AuthProvider>,
    document.getElementById('root')
);
