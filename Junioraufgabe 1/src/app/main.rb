# Die Main "Klasse"
class Main

  attr_accessor :storages
  attr_accessor :output_storage
  attr_accessor :packages
  attr_accessor :used
  attr_accessor :data
  attr_accessor :arg

  def initialize(arg)
    self.arg = arg
    self.storages = []
    self.output_storage = Fach.new(10, 0)
    self.data = []
    self.packages = 0
    self.used = 0
  end

  def sum_storage(storages)
    storages.reduce(0) { |a, e| a + e.amount }
  end

  def subsets(set)
    2.upto(10).flat_map { |n| set.combination(n).to_a }
  end

  def find_fitting(sets, limit)
    sets.each { |x| return x if sum_storage(x) == limit }
    []
  end

  def first_non_empty_storage
    storages.each { |x| return x if x.amount != 0 }
    false
  end

  def hope_for_better_data
    sto = first_non_empty_storage
    return false if sto == false
    self.used += sto.amount
    sto.to output_storage
    print "FACH(#{sto.id + 1}), "
    sto.amount = data.pop unless data.empty?
    true
  end

  def empty_optimal_data
    subs = subsets(storages)
    i = 20 - output_storage.amount
    i += 1 while find_fitting(subs, i).empty?
    fitting = find_fitting(subs, i)
    fitting.each do |x|
      print "FACH(#{x.id + 1}), "
      self.used += x.amount
      x.to output_storage
      x.amount = data.pop unless data.empty?
    end
    $stdout.flush
    puts "VERPACKEN() = #{output_storage.amount}"
    self.packages += 1
    output_storage.amount = 0
  end

  def make_round
    if (sum_storage storages) + output_storage.amount < 20
      hope_for_better_data
    else
      empty_optimal_data
    end
  end

  def run
    0.upto(9) { |x| storages << Fach.new(x, 0) }
    content = File.read arg
    content.each_line { |l| data << l.to_i }
    data.reverse!
    (0..9).each { |x| storages[x].amount = data.pop unless data.empty? }
    (0..9).each { |x| print "Fach(#{x+1}), " }
    print " (Erstes Befüllen fertig)"
    $stdout.flush
    puts ''
    make_round while (sum_storage storages) + output_storage.amount > 0 && first_non_empty_storage
    puts ''
    puts "Hergestellte Packungen: #{packages}"
    puts "Verbrauchte Ballons: #{used}"
    puts "Übrig in Fächern: #{sum_storage storages}"
    puts "Übrig in Ausgabe: #{output_storage.amount}"
  end

end
