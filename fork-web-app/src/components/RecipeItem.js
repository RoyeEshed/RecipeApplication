import React from 'react'
import { Button } from 'react-bootstrap'
import { useHistory } from "react-router-dom"
import { useAuth } from "../context/AuthContext"

export default function RecipeItem({recipeVal}) {
    const history = useHistory()

    function handleButtonClick() {
        history.push("/edit")
    }

    return (
        <div>
            <li> {recipeVal} </li>
            <Button onClick={handleButtonClick}>Edit</Button>
        </div>
    )
}
