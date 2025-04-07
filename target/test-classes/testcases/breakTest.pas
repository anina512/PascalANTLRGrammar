program BreakTest;
var
  i: Integer;
begin
  for i := 1 to 10 do
  begin
    writeln(i);
    if i = 5 then
      break;  { Exit the loop when i reaches 5 }
  end;
  
  writeln('Loop exited with break');
end.