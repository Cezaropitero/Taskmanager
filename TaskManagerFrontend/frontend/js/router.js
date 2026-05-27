import { initLoginPage } from "./pages/login.js";
import { initRegisterPage } from "./pages/register.js";
import { initDashboard } from "./pages/dashboard.js";

const app = document.getElementById("app");

export function initPage(page) {

    if (page === "landing") {
        console.log("Landing init");
    }

    if (page === "login") {
        console.log("Login init");
        initLoginPage();
    }

    if (page === "register") {
        console.log("Register init");
        initRegisterPage();
    }

    if (page === "dashboard") {
        console.log("Dashboard init");
        initDashboard();
    }
}

export async function loadPage(page, addToHistory = true) {
    try {
        const response = await fetch(`app/pages/${page}/${page}.html`);

        if (!response.ok) {
            throw new Error("Page not found: " + page);
        }

        const html = await response.text();

        app.innerHTML = html;

        if (addToHistory) {
            history.pushState({ page }, "", `#${page}`);
        }

        initPage(page);

    } catch (err) {
        console.error(err);
        app.innerHTML = "<h2>Page error</h2>";
    }
}

window.navigateTo = loadPage;

window.addEventListener("popstate", (event) => {
    const page = event.state?.page || "landing";
    loadPage(page, false);
});
