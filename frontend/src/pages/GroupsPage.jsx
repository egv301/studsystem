import { useEffect, useState } from 'react';
import EntityList from '../components/EntityList.jsx';

export default function GroupsPage({ api }) {
  const [groups, setGroups] = useState([]);
  const [title, setTitle] = useState('');
  const [editing, setEditing] = useState(null);

  const load = () => api.get('/api/admin/group-list').then(setGroups);

  useEffect(() => {
    load();
  }, []);

  async function save(event) {
    event.preventDefault();
    if (editing) {
      await api.put('/api/admin/update-group', { id: editing, title }, 'Group updated');
    } else {
      await api.post('/api/admin/add-group', { title }, 'Group added');
    }
    setTitle('');
    setEditing(null);
    load();
  }

  return (
    <section className="card">
      <h2>Groups</h2>
      <form className="grid" onSubmit={save}>
        <label>Title<input value={title} onChange={(event) => setTitle(event.target.value)} required /></label>
        <button>{editing ? 'Update' : 'Add'} group</button>
      </form>
      <EntityList
        items={groups}
        render={(group) => group.title}
        onEdit={(group) => {
          setEditing(group.id);
          setTitle(group.title);
        }}
        onDelete={(group) => api.del(`/api/admin/delete-group/${group.id}`, null, 'Group deleted').then(load)}
      />
    </section>
  );
}
