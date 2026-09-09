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
      setMessage(`Short URL: ${res.data.shortUrl}`);
      setFullUrl("");
      setCustomAlias("");
      loadUrls();
    } catch (err) {
      setMessage("Error: " + err.response?.data || "Unknown error");
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
    <div style={{ padding: "2rem", fontFamily: "Arial" }}>
      <h1>URL Shortener</h1>

      <div style={{ marginBottom: "1rem" }}>
        <input
          type="text"
          placeholder="Full URL"
          value={fullUrl}
          onChange={(e) => setFullUrl(e.target.value)}
          style={{ width: "300px", marginRight: "10px" }}
        />
        <input
          type="text"
          placeholder="Custom alias (optional)"
          value={customAlias}
          onChange={(e) => setCustomAlias(e.target.value)}
          style={{ width: "200px", marginRight: "10px" }}
        />
        <button onClick={handleShorten}>Shorten</button>
      </div>

      {message && <p>{message}</p>}

      <h2>All URLs</h2>
      <table border="1" cellPadding="10">
        <thead>
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
                <button onClick={() => handleDelete(u.alias)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default App;
