import { register } from "../services/authService.js";

export function initRegisterPage() {

    const form = document.querySelector("form");
    if (!form) {
        return;
    }

    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;

        try {
            const res = await register(email, password);
            const responseText = await res.text();

            console.log("Register response status:", res.status);
            console.log("Register response body:", responseText);

            let data = {};
            if (responseText) {
                data = JSON.parse(responseText);
            }

            if (res.ok) {
                alert("Registered");
                navigateTo("login");
                return;
            }

            alert(data.message || "Registration failed");
        } catch (error) {
            console.error("Register failed:", error);
            alert("Cannot connect to backend");
        }
    });
}
