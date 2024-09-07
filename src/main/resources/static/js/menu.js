// Function to filter menu items based on search input
function filterMenu() {
    const searchTerm = document.getElementById("menuSearchInput").value.toLowerCase();
    const menuItems = document.querySelectorAll(".menuItem");

    menuItems.forEach(item => {
        const title = item.querySelector(".card-title").textContent.toLowerCase();
        const description = item.querySelector(".card-text").textContent.toLowerCase();

        if (title.includes(searchTerm) || description.includes(searchTerm)) {
            item.style.display = "block";
        } else {
            item.style.display = "none";
        }
    });
}

// Add event listeners for Enter key and search button
document.getElementById("menuSearchInput").addEventListener("keyup", function(event) {
    if (event.key === "Enter") {
        filterMenu();
    }
});

document.getElementById("menuSearchButton").addEventListener("click", filterMenu);