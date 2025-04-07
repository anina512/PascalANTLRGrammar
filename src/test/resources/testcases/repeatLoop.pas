program RepeatLoopTest;
var
  i: Integer;
begin
  i := 1;
  repeat
    writeln(i);
    i := i + 1;
  until i > 5;
end.