export function createApi(auth, setMessage) {
  async function request(path, options = {}) {
    setMessage(null);

    const response = await fetch(path, {
      ...options,
      headers: {
        'Content-Type': 'application/json',
        ...(auth?.token ? { Authorization: `Bearer ${auth.token}` } : {}),
        ...(options.headers || {})
      }
    });

    if (!response.ok) {
      let errorText = `Request failed with ${response.status}`;
      try {
        const data = await response.json();
        errorText = data.message || data.errorMessage || Object.values(data).join(', ') || errorText;
      } catch (error) {
        // Keep the HTTP status fallback when the server returns no JSON body.
      }
      throw new Error(errorText);
    }

    if (response.status === 204) {
      return null;
    }

    const text = await response.text();
    return text ? JSON.parse(text) : null;
  }

  async function run(action, successMessage) {
    try {
      const result = await action();
      if (successMessage) {
        setMessage({ type: 'success', text: successMessage });
      }
      return result;
    } catch (error) {
      setMessage({ type: 'error', text: error.message });
      throw error;
    }
  }

  return {
    get: (path) => run(() => request(path)),
    post: (path, body, message) => run(() => request(path, { method: 'POST', body: JSON.stringify(body) }), message),
    put: (path, body, message) => run(() => request(path, { method: 'PUT', body: JSON.stringify(body) }), message),
    del: (path, body, message) => run(() => request(path, {
      method: 'DELETE',
      ...(body ? { body: JSON.stringify(body) } : {})
    }), message)
  };
}
