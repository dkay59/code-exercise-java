import axios from "axios";

const API = axios.create({
  baseURL: "http://localhost:8080/"
});

// POST /shorten
export const shortenUrl = (fullUrl, customAlias) =>
  API.post("/shorten", { fullUrl, customAlias });

// GET /urls
export const listUrls = () =>
  API.get("/urls");

// DELETE /{alias}
export const deleteUrl = (alias) =>
  API.delete(`/${alias}`);
