import React, { useEffect, useState } from "react"
import { Card, Button, Alert } from "react-bootstrap"
import { useAuth } from "../context/AuthContext"
import { Link, useHistory } from "react-router-dom"
import firebase from "../firebase"
import styles from './mystyle.module.css'; 
import RecipeItem from "./RecipeItem"
import "bootstrap/dist/css/bootstrap.min.css"

export default function EditRecipe() {
    const [error, setError] = useState("")
  const [recipeList, setRecipeList] = useState([])
  const { currentUser, logout } = useAuth()
  const [val, setVal] = useState("")
  const history = useHistory()

  async function handleLogout() {
    setError("")

    try {
      await logout()
      history.push("/login")
    } catch {
      setError("Failed to log out")
    }
  }

  useEffect(() => {
    const recipeRef = firebase.database().ref('recipes');
    recipeRef.on('value', (snapshot) => {
        const recipe = snapshot.val()
        const recipesList = []
        recipesList.push(recipe[85])
        console.log(recipesList)
        setRecipeList(recipesList)
    })
  }, [])

  function handleButtonClick() {
    history.push("/")
 }

 function handleButtonClick2() {
    setVal("New changes submitted")
 }

  return (
    <>
      <Card>
        <Card.Body>
            {val && <Alert variant="success">{val}</Alert>}
            <h1>Recipes: {val}</h1>
                { 
                (recipeList).map((recipe) => (
                <div>
                    <h1>{recipe.name} - </h1>
                    <h2>Ingredients</h2>
                    {recipe.ingredients.map((ingredient) => (
                        <div>
                       <input defaultValue={(ingredient).amount}/><input defaultValue={(ingredient).ingredientName}/>
                       </div>
                    ))}
                    <h2>Directions</h2>
                    {recipe.directions.map((direction) => (
                       <div>
                        {(direction).directionNumber} : <input defaultValue={(direction).directionText}/>
                       </div>
                    ))}
                    <h2>Tags</h2>
                    {recipe.tags.map((tag) => (
                       <div>
                           <input defaultValue={tag}/>
                       </div> 
                    ))}
                </div>))
                }
                <Button onClick={handleButtonClick2}>Submit</Button><Button onClick={handleButtonClick}>Exit</Button>
        </Card.Body>
      </Card>
    </>
  )
}
