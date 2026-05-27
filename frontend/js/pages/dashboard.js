let tasks = [
    { id: 1, title: "Learn Spring Boot", status: "TODO" },
    { id: 2, title: "Build API", status: "IN_PROGRESS" },
    { id: 3, title: "Finish project", status: "DONE" }
];

export function initDashboard() {
    const addButton = document.getElementById("addTaskButton");
    const titleInput = document.getElementById("title");
    const taskList = document.getElementById("taskList");

    if (!addButton || !titleInput || !taskList) {
        console.error("Dashboard elements not found");
        return;
    }

    addButton.addEventListener("click", addTask);
    titleInput.addEventListener("keydown", (event) => {
        if (event.key === "Enter") {
            addTask();
        }
    });

    taskList.addEventListener("click", (event) => {
        const deleteButton = event.target.closest("[data-delete-task-id]");

        if (!deleteButton) {
            return;
        }

        deleteTask(Number(deleteButton.dataset.deleteTaskId));
    });

    taskList.addEventListener("change", (event) => {
        const statusSelect = event.target.closest("[data-task-status-id]");

        if (!statusSelect) {
            return;
        }

        updateTaskStatus(Number(statusSelect.dataset.taskStatusId), statusSelect.value);
    });

    render();
    initDashboardNavigation();
    initSettingsNavigation();
}

function render() {
    const list = document.getElementById("taskList");

    list.innerHTML = "";

    let todo = 0;
    let progress = 0;
    let done = 0;

    tasks.forEach((task) => {
        if (task.status === "TODO") todo++;
        if (task.status === "IN_PROGRESS") progress++;
        if (task.status === "DONE") done++;

        const taskElement = document.createElement("div");
        taskElement.className = "task";
        taskElement.innerHTML = `
            <div class="task-info">
                <strong>${task.title}</strong>
                <span class="status ${getStatusClass(task.status)}">${task.status}</span>
            </div>
            <div class="task-actions">
                <select class="status-select" data-task-status-id="${task.id}">
                    <option value="TODO" ${task.status === "TODO" ? "selected" : ""}>Todo</option>
                    <option value="IN_PROGRESS" ${task.status === "IN_PROGRESS" ? "selected" : ""}>In progress</option>
                    <option value="DONE" ${task.status === "DONE" ? "selected" : ""}>Done</option>
                </select>
                <button class="delete" type="button" data-delete-task-id="${task.id}">Delete</button>
            </div>
        `;

        list.appendChild(taskElement);
    });

    document.getElementById("total").innerText = tasks.length;
    document.getElementById("todo").innerText = todo;
    document.getElementById("progress").innerText = progress;
    document.getElementById("done").innerText = done;
}

function getStatusClass(status) {
    if (status === "TODO") return "todo";
    if (status === "IN_PROGRESS") return "progress";
    return "done";
}

function addTask() {
    const titleInput = document.getElementById("title");
    const title = titleInput.value.trim();

    if (!title) {
        return;
    }

    tasks.push({
        id: Date.now(),
        title,
        status: "TODO"
    });

    titleInput.value = "";
    render();
}

function deleteTask(id) {
    tasks = tasks.filter((task) => task.id !== id);
    render();
}

function updateTaskStatus(id, status) {
    tasks = tasks.map((task) => {
        if (task.id !== id) {
            return task;
        }

        return {
            ...task,
            status
        };
    });

    render();
}

function initDashboardNavigation() {
    const viewButtons = document.querySelectorAll("[data-dashboard-view-target]");
    const views = document.querySelectorAll("[data-dashboard-view]");

    viewButtons.forEach((button) => {
        button.addEventListener("click", () => {
            const targetView = button.dataset.dashboardViewTarget;

            viewButtons.forEach((item) => item.classList.remove("active"));
            button.classList.add("active");

            views.forEach((view) => {
                view.classList.toggle("hidden", view.dataset.dashboardView !== targetView);
            });
        });
    });
}

function initSettingsNavigation() {
    const optionButtons = document.querySelectorAll("[data-settings-section-target]");
    const sections = document.querySelectorAll("[data-settings-section]");

    optionButtons.forEach((button) => {
        button.addEventListener("click", () => {
            const targetSection = button.dataset.settingsSectionTarget;

            optionButtons.forEach((item) => item.classList.remove("active"));
            button.classList.add("active");

            sections.forEach((section) => {
                section.classList.toggle("hidden", section.dataset.settingsSection !== targetSection);
            });
        });
    });
}
