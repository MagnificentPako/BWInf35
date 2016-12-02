require "chunky_png"

class Position

  attr_reader :x
  attr_reader :y

  def initialize(x,y)
      @x = x
      @y = y
  end

end

def process name;
  @png = ChunkyPNG::Image.from_file name

  @width = @png.dimension.width
  @height = @png.dimension.height

  @marked = []


  def in_bounds x,y; (x >= 0 && x < @width-1 && y>=0 && y<@height-1) end

  def mark_neighbours x,y
    possibilities = [[x+1,y],[x+1,y+1],[x,y+1],[x-1,y],[x-1,y-1],[x,y-1]]
    possibilities.each do |po|
      if(in_bounds po[0],po[1] ) then
        if @png[x,y] == @png[po[0],po[1]] then
          @marked << Position.new(po[0],po[1])
        end
      end
    end
  end

  @png.height.times do |y|
    @png.row(y).each.with_index do |pixel, x|
      mark_neighbours x,y
    end
  end

  @marked.each do |pos|
      white = ChunkyPNG::Color.rgb(255,255,255)
      @png[pos.x, pos.y] = white unless @png[pos.x, pos.y] == white
  end

  @png.save "out.#{name}"
end

#kopiert von http://www.skorks.com/2010/03/timing-ruby-code-it-is-easy-with-benchmark/ um die methode zu "timen"
#leicht abgeändert, damit es in Sekunden und nicht Millisekunden timed.
def time_method(method=nil, *args)
  beginning_time = Time.now
  if block_given?
    yield
  else
    self.send(method, args)
  end
  end_time = Time.now
  puts "Time elapsed #{(end_time - beginning_time)} seconds"
end

# Ich habe direkt alle dateien bearbeiten lassen (mit "ruby main.rb *.png").
time_method do
  ARGV.each do |n|
    puts "Bearbeite #{n}"
    if File.file? n
      time_method do
        process n
      end
    else
      puts "#{n} ist keine Datei!"
    end
  end
  puts "Gesamtzeit:"
end
