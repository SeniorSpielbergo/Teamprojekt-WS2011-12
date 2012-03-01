PDFLIST=userguide.pdf

all: $(PDFLIST) clean

%.pdf: %.tex
	pdflatex $<
	pdflatex $<

clean:
	rm -f *.log *.aux *~ *.toc
