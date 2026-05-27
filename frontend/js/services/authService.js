export async function login(email, password) {
    console.log("Sending POST /api/auth/login", { email });

    return fetch("http://localhost:8080/api/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password })
    });
}

export async function register(email, password) {
    console.log("Sending POST /api/auth/register", { email });

    return fetch("http://localhost:8080/api/auth/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password })
    });
}
