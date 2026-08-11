export default function SubjectBucket({ title, subjects, action, onAction }) {
  return (
    <div>
      <h3>{title}</h3>
      <div className="list">
        {subjects.map((subject) => (
          <div className="item" key={subject.id}>
            <span>{subject.title}</span>
            <button className="secondary" onClick={() => onAction(subject)}>{action}</button>
          </div>
        ))}
      </div>
    </div>
  );
}
