program ClassTest;

var
  myCounter: Counter;

class Counter;
  value: Integer;
  
  constructor Create(initialValue: Integer);
  begin
    value := initialValue;
    writeln('Counter created with value: ', value);
  end;
  
  destructor Destroy;
  begin
    writeln('Counter destroyed. Final value: ', value);
  end;
  
  procedure Increment;
  begin
    value := value + 1;
    writeln('Counter incremented to: ', value);
  end;
end;

begin
  myCounter := Counter.Create(5);
  myCounter.Increment;
  myCounter.Increment;
  myCounter.Destroy;
end.