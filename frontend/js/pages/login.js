import { login } from "../services/authService.js";

export function initLoginPage() {

    const form = document.querySelector("form");
    if (!form) {
        return;
    }

    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;

        try {
            const res = await login(email, password);
            const responseText = await res.text();

            console.log("Login response status:", res.status);
            console.log("Login response body:", responseText);

            let data = {};
            if (responseText) {
                data = JSON.parse(responseText);
            }

            if (res.ok) {
                navigateTo("dashboard");
                return;
            }

            alert(data.message || "Wrong login");
        } catch (error) {
            console.error("Login failed:", error);
            alert("Cannot connect to backend");
        }
    });
}
