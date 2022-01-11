/**
 * 
 */
var dropdownMenu = document.querySelector(".dropdown-menu")
var dropdownButton = document.querySelector(".dropdown-button")

dropdownButton.addEventListener("mouseover", function(event) {
  if (this.active) {
    dropdownMenu.classList.remove("active")
  } else {
    dropdownMenu.classList.add("active")
  }

  this.active = !this.active
})

dropdownMenu.addEventListener("mouseleave", function(event) {

    dropdownMenu.classList.remove("active")
})

dropdownButton.active = false


