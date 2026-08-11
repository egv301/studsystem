import { useEffect, useState } from 'react';
import EntityList from '../components/EntityList.jsx';

const emptyStudent = { firstname: '', lastname: '', group: '' };

export default function StudentsPage({ api }) {
  const [students, setStudents] = useState([]);
  const [groups, setGroups] = useState([]);
  const [form, setForm] = useState(emptyStudent);

  const load = () => Promise.all([
    api.get('/api/admin/student-list').then(setStudents),
    api.get('/api/admin/showAddStudentForm').then(setGroups)
  ]);

  useEffect(() => {
    load();
  }, []);

  async function save(event) {
    event.preventDefault();
    const body = { ...form, group: Number(form.group) };
    if (form.id) {
      await api.put('/api/admin/update-student', body, 'Student updated');
    } else {
      await api.post('/api/admin/add-student', body, 'Student added');
    }
    setForm(emptyStudent);
    load();
  }

  return (
    <section className="card">
      <h2>Students</h2>
      <form className="grid" onSubmit={save}>
        <label>First name<input value={form.firstname} onChange={(event) => setForm({ ...form, firstname: event.target.value })} required /></label>
        <label>Last name<input value={form.lastname} onChange={(event) => setForm({ ...form, lastname: event.target.value })} required /></label>
        <label>Group<select value={form.group} onChange={(event) => setForm({ ...form, group: event.target.value })} required>
          <option value="">Select group</option>
          {groups.map((group) => <option key={group.id} value={group.id}>{group.title}</option>)}
        </select></label>
        <button>{form.id ? 'Update' : 'Add'} student</button>
      </form>
      <EntityList
        items={students}
        render={(student) => `${student.firstname} ${student.lastname}`}
        onEdit={(student) => setForm(student)}
        onDelete={(student) => api.del(`/api/admin/delete-student/${student.id}`, null, 'Student deleted').then(load)}
      />
    </section>
  );
}
