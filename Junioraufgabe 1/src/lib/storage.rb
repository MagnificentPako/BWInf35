# Diese Klasse wird benutzt um die Faecher zu "repraesentieren"
class Fach

  attr_accessor :id
  attr_accessor :amount

  def initialize(eyedee, amount) # Rechtschreibfehler ist extra
    self.id = eyedee
    self.amount = amount
  end

  def to(storage)
    storage.amount += amount
    self.amount = 0
  end

end
