export async function getTasks() {
    return fetch("/api/tasks");
}