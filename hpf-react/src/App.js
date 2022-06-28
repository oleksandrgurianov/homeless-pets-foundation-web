import React from 'react'
import './styles/All/App.css'
import Header from './components/Header'
import Footer from './components/Footer'
import {Route, Routes} from 'react-router-dom'
import HomePage from './pages/All/HomePage'
import PetsPage from './pages/All/PetsPage'
import PetPage from './pages/All/PetPage'
import NotFoundPage from './pages/All/NotFoundPage'
import AddPetPage from './pages/Administrator/AddPetPage'
import DonationsPage from './pages/Administrator/DonationsPage'
import DonateCustPage from './pages/Customer/DonateCustPage'
import DonatePage from './pages/All/DonatePage'
import LogInPage from './pages/All/LogInPage'
import RequireAuth from './components/RequireAuth'

function App() {
    return (
        <div className={'App'}>
            <Header/>
            <div className={'Body'}>
                <Routes>
                    <Route path={'/'} element={<HomePage/>}/>
                    <Route path={'/pets/categories/:type'} element={<PetsPage/>}/>
                    <Route path={'/pets/categories/:type/:id'} element={<PetPage/>}/>
                    <Route path={'/*'} element={<NotFoundPage/>}/>

                    <Route element={<RequireAuth allowedRoles={['ADMIN']}/>}>
                        <Route path={'/pets/addPet'} element={<AddPetPage/>}/>
                        <Route path={'/donations'} element={<DonationsPage/>}/>
                    </Route>

                    <Route element={<RequireAuth allowedRoles={['CUST']}/>}>
                        <Route path={'/donateCust'} element={<DonateCustPage/>}/>
                    </Route>

                    <Route path={'/donate'} element={<DonatePage/>}/>
                    <Route path={'/logIn'} element={<LogInPage/>}/>
                </Routes>
            </div>
            <Footer/>
        </div>
    );
}

export default App;
