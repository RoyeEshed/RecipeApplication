import React, { useEffect, useState } from "react"
import { Card, Button, Alert } from "react-bootstrap"
import { useAuth } from "../context/AuthContext"
import { Link, useHistory } from "react-router-dom"
import firebase from "../firebase"
import styles from './mystyle.module.css'; 
import RecipeItem from "./RecipeItem"
import "bootstrap/dist/css/bootstrap.min.css"

export default function Dashboard() {
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
        for (let id in recipe) {
            recipesList.push(recipe[id])
        }
        console.log(recipesList)
        setRecipeList(recipesList)
    })
  }, [])


  return (
    <>
      <Card>
        <Card.Body>
            <h1>Recipes: {val}</h1>
                { 
                (recipeList).map((recipe) => (
                <RecipeItem key={recipe.id} recipeVal={recipe.name} />)) 
                }
        </Card.Body>
      </Card>
      <div className="w-100 text-center mt-2">
        <Button variant="link" onClick={handleLogout}>
          Log Out
        </Button>
      </div>
    </>
  )
}