import React, { useState, useEffect } from "react";
import { shortenUrl, listUrls, deleteUrl } from "./api";

function App() {
  const [fullUrl, setFullUrl] = useState("");
  const [customAlias, setCustomAlias] = useState("");
  const [urls, setUrls] = useState([]);
  const [message, setMessage] = useState("");

  const loadUrls = async () => {
    const res = await listUrls();
    setUrls(res.data);
  };

  useEffect(() => {
    loadUrls();
  }, []);

  const handleShorten = async () => {
    try {
      const res = await shortenUrl(fullUrl, customAlias);
      setMessage(`Short URL created: ${res.data.shortUrl}`);
      setFullUrl("");
      setCustomAlias("");
      loadUrls();
    } catch (err) {
      setMessage("Error: " + (err.response?.data || "Unknown error"));
    }
  };

  const handleOpen = async (alias) => {
    try {
      const res = await fetch(`http://localhost:8080/${alias}`);
      const data = await res.json();
      window.open(data.fullUrl, "_blank");
    } catch (err) {
      setMessage("Error opening URL");
    }
  };

  const handleDelete = async (alias) => {
    try {
      await deleteUrl(alias);
      loadUrls();
    } catch (err) {
      setMessage("Error deleting alias");
    }
  };

  return (
    <div className="container mt-5">
      <h1 className="text-center mb-4">URL Shortener</h1>

      {message && (
        <div className="alert alert-info text-center">{message}</div>
      )}

      <div className="card p-4 mb-4 shadow-sm">
        <h4 className="mb-3">Create Short URL</h4>

        <div className="mb-3">
          <label className="form-label">Full URL</label>
          <input
            type="text"
            className="form-control"
            placeholder="https://example.com"
            value={fullUrl}
            onChange={(e) => setFullUrl(e.target.value)}
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Custom Alias (optional)</label>
          <input
            type="text"
            className="form-control"
            placeholder="my-alias"
            value={customAlias}
            onChange={(e) => setCustomAlias(e.target.value)}
          />
        </div>

        <button className="btn btn-primary w-100" onClick={handleShorten}>
          Shorten URL
        </button>
      </div>

      <h3 className="mb-3">All URLs</h3>

      <table className="table table-striped table-hover shadow-sm">
        <thead className="table-dark">
          <tr>
            <th>Alias</th>
            <th>Full URL</th>
            <th>Short URL</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {urls.map((u) => (
            <tr key={u.alias}>
              <td>{u.alias}</td>
              <td>{u.fullUrl}</td>
              <td>
                <a href={u.shortUrl} target="_blank" rel="noreferrer">
                  {u.shortUrl}
                </a>
              </td>
              <td>
                <button
                  className="btn btn-danger btn-sm"
                  onClick={() => handleDelete(u.alias)}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default App;
