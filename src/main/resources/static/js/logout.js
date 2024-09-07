// Logout button function
function confirmLogout() {
    const confirmLogout = confirm("Are you sure you want to log out?");
    if (confirmLogout) {
        // Perform logout actions here
        window.location.href = "/index";
    }
}

// Add an event listener to the "Logout" button
document.getElementById("logoutBtn").addEventListener("click", function() {
    confirmLogout();
});