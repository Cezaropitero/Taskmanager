import { loadPage } from "./router.js";

console.log("TaskManager started");

const startPage = location.hash.replace("#", "") || "landing";

history.replaceState({ page: startPage }, "", `#${startPage}`);

loadPage(startPage, false);

import "./pages/register.js";
import "./pages/login.js";
