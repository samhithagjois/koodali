import {booleanAttribute, Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-attendance-overview',
  standalone : true,
  imports: [
    FormsModule,
    RouterLink
  ],
  templateUrl: './attendance-overview.html',
  styleUrl: './attendance-overview.css',
})
export class AttendanceOverview implements OnInit {

  rows: any[] = [];
  columns: any[] = [];
  row: any;
  col: any;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadAttendances();
  }
/*this whole part is by chatgpt. I have to check this and understand it*/
  loadAttendances() {
    this.http.get<any[]>(`http://localhost:8080/api/teachers/attendance`)
      .subscribe(data => {
        this.initializeTable(data);
      });
  }

  initializeTable(data: any[]) {
    const columnSet = new Set<string>();
    const rowMap = new Map<string, any>();

    data.forEach(entry => {
      columnSet.add(entry.week);

      if (!rowMap.has(entry.studentId)) {
        rowMap.set(entry.studentId, {
          id: rowMap.size + 1,
          studentId: entry.studentId,
          studentName: entry.studentName,
          attendance: {}
        });
      }

      rowMap.get(entry.studentId).attendance[entry.week] = entry.attended;
    });

    this.columns = Array.from(columnSet).map(w => ({
      key: w,
      label: w.toUpperCase()
    }));

    this.rows = Array.from(rowMap.values());
  }

  addRow() {
    this.rows.push({
      id: this.rows.length + 1,
      studentId: '',
      studentName: '',
      attendance: {}
    });
  }

  addColumn() {
    const index = this.columns.length + 1;
    const key = `w${index}`;

    this.columns.push({
      key,
      label: `W${index}`
    });

    // initialize column for all rows
    this.rows.forEach(r => r.attendance[key] = false);
  }

  toggleAttendance(row: any, columnKey: string) {
    row.attendance[columnKey] = !row.attendance[columnKey];

    this.http.patch(
      `http://localhost:8080/api/teachers/attendance`,
      {
        studentId: row.studentId,
        week: columnKey,
        attended: row.attendance[columnKey]
      }
    ).subscribe();
  }
}
