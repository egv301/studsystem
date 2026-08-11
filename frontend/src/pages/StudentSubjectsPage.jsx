import { useEffect, useState } from 'react';
import SubjectBucket from '../components/SubjectBucket.jsx';

export default function StudentSubjectsPage({ api }) {
  const [students, setStudents] = useState([]);
  const [selected, setSelected] = useState('');
  const [data, setData] = useState(null);

  useEffect(() => {
    api.get('/api/admin/student-list').then(setStudents);
  }, []);

  useEffect(() => {
    if (selected) {
      api.get(`/api/admin/getStudentsSubjects/${selected}`).then(setData);
    } else {
      setData(null);
    }
  }, [selected]);

  function refreshSubjects() {
    return api.get(`/api/admin/getStudentsSubjects/${selected}`).then(setData);
  }

  function changeSubject(subject, method) {
    const endpoint = method === 'post' ? 'addSubjectToStudent' : 'removeSubjectFromStudent';
    return api[method](`/api/admin/${endpoint}`, {
      student: Number(selected),
      subject: subject.id
    }, 'Student subjects updated').then(refreshSubjects);
  }

  return (
    <section className="card">
      <h2>Student subjects</h2>
      <label>Student<select value={selected} onChange={(event) => setSelected(event.target.value)}>
        <option value="">Select student</option>
        {students.map((student) => <option key={student.id} value={student.id}>{student.firstname} {student.lastname}</option>)}
      </select></label>
      {data && (
        <div className="grid">
          <SubjectBucket title="Assigned" subjects={data.studentsSubject} action="Remove" onAction={(subject) => changeSubject(subject, 'del')} />
          <SubjectBucket title="Available" subjects={data.subjectsToRegister} action="Add" onAction={(subject) => changeSubject(subject, 'post')} />
        </div>
      )}
    </section>
  );
}
