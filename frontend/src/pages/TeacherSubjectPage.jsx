import { useEffect, useState } from 'react';
import EntityList from '../components/EntityList.jsx';

export default function TeacherSubjectPage({ api, subjectId }) {
  const emptyAssignment = { title: '', subject: Number(subjectId) };
  const [assignments, setAssignments] = useState([]);
  const [assignmentForm, setAssignmentForm] = useState(emptyAssignment);
  const [attendanceDate, setAttendanceDate] = useState(new Date().toISOString().slice(0, 10));
  const [attendance, setAttendance] = useState([]);
  const [pointsAssignment, setPointsAssignment] = useState('');
  const [points, setPoints] = useState(null);

  const loadAssignments = () => api.get(`/api/teacher/assignment-list/${subjectId}`).then(setAssignments);

  useEffect(() => {
    setAssignmentForm({ title: '', subject: Number(subjectId) });
    setPointsAssignment('');
    setPoints(null);
    loadAssignments();
  }, [subjectId]);

  async function saveAssignment(event) {
    event.preventDefault();
    if (assignmentForm.id) {
      await api.put('/api/teacher/update-assignment', assignmentForm, 'Assignment updated');
    } else {
      await api.post('/api/teacher/add-assignment', assignmentForm, 'Assignment added');
    }
    setAssignmentForm({ title: '', subject: Number(subjectId) });
    loadAssignments();
  }

  function loadAttendance() {
    api.get(`/api/teacher/getAttendanceList/${subjectId}/${attendanceDate}`).then(setAttendance);
  }

  function saveAttendance(row, attended) {
    const next = { ...row, attended };
    setAttendance(attendance.map((item) => (item.studentId === row.studentId ? next : item)));
    api.post('/api/teacher/attendance', {
      student: row.studentId,
      subject: Number(subjectId),
      date: attendanceDate,
      attended
    }, 'Attendance saved');
  }

  function loadPoints(id) {
    setPointsAssignment(id);
    if (id) {
      api.get(`/api/teacher/getPointsList/${id}`).then(setPoints);
    } else {
      setPoints(null);
    }
  }

  function savePoints(row, nextPoints) {
    setPoints({
      ...points,
      pointsAssignmentList: points.pointsAssignmentList.map((item) =>
        item.student === row.student ? { ...item, points: nextPoints } : item
      )
    });
    api.post('/api/teacher/addPointsToAssignment', {
      student: row.student,
      subjectAssignment: Number(pointsAssignment),
      points: nextPoints
    }, 'Points saved');
  }

  return (
    <>
      <div className="card">
        <h3>Assignments</h3>
        <form className="grid" onSubmit={saveAssignment}>
          <label>Title<input value={assignmentForm.title} onChange={(event) => setAssignmentForm({ ...assignmentForm, title: event.target.value })} required /></label>
          <button>{assignmentForm.id ? 'Update' : 'Add'} assignment</button>
        </form>
        <EntityList
          items={assignments}
          render={(assignment) => assignment.title}
          onEdit={(assignment) => setAssignmentForm({ ...assignment, subject: Number(subjectId) })}
          onDelete={(assignment) => api.del(`/api/teacher/delete-assignment/${assignment.id}`, null, 'Assignment deleted').then(loadAssignments)}
        />
      </div>
      <div className="card">
        <h3>Attendance</h3>
        <div className="grid">
          <label>Date<input type="date" value={attendanceDate} onChange={(event) => setAttendanceDate(event.target.value)} /></label>
          <button onClick={loadAttendance}>Load attendance</button>
        </div>
        <div className="list">
          {attendance.map((row) => (
            <div className="item" key={row.studentId}>
              <span>{row.firstName} {row.lastName}</span>
              <label className="inline">
                <input type="checkbox" checked={row.attended} onChange={(event) => saveAttendance(row, event.target.checked)} />
                Attended
              </label>
            </div>
          ))}
        </div>
      </div>
      <div className="card">
        <h3>Points</h3>
        <label>Assignment<select value={pointsAssignment} onChange={(event) => loadPoints(event.target.value)}>
          <option value="">Select assignment</option>
          {assignments.map((assignment) => <option key={assignment.id} value={assignment.id}>{assignment.title}</option>)}
        </select></label>
        {points && (
          <div className="list">
            {points.pointsAssignmentList.map((row) => (
              <div className="item" key={row.student}>
                <span>{row.studentFirstname} {row.studentLastname}</span>
                <input type="number" min="0" value={row.points} onChange={(event) => savePoints(row, Number(event.target.value))} />
              </div>
            ))}
          </div>
        )}
      </div>
    </>
  );
}
