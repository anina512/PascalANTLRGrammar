program SimpleProcedureTest;
var
  x: Integer;

procedure PrintMessage;
begin
  writeln('Hello from procedure');
end;

begin
  x := 5;
  writeln('Value is: ', x);
  PrintMessage;
  writeln('Procedure completed');
end.