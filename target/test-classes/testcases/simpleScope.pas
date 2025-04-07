program SimpleScopeTest;
var
  x: Integer;

procedure OuterProc;
var
  y: Integer;
begin
  y := 20;
  writeln('In OuterProc: x = ', x);
  writeln('In OuterProc: y = ', y);
  
  x := x + 5;
end;

begin
  x := 10;
  writeln('In main: x = ', x);
  
  OuterProc;
  
  writeln('Back in main: x = ', x);
end.