import React from "react"
import Signup from "./Signup";
import { Container } from "react-bootstrap"
import { AuthProvider } from "../context/AuthContext"
import {BrowserRouter as Router, Switch, Route} from 
"react-router-dom"
import Login from "./Login";
import PrivateRoute from "./PrivateRoute";
import Dashboard from "./Dashboard";
import EditRecipe from "./EditRecipe"

function App() {
 return (
    <Container 
        className ="d-flex align-items-center justify-content-center" 
            style={{ minHeight: "100vh"}}>
            <div className="w-100" style={{ maxWidth: "900px"}}>
            <Router>
                <AuthProvider>
                    <Switch>
                        <PrivateRoute exact path="/" component={Dashboard} />
                        <Route path="/signup" component={Signup} />
                        <Route path="/edit" component={EditRecipe} />
                        <Route path="/login" component={Login} />
                    </Switch>
                </AuthProvider>
            </Router>
        </div>
    </Container>
 )
}

export default App;
