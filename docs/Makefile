PANDOC ?= pandoc

# convert the markdown template to pdf
pdftemplate: ../report_template.md
	$(PANDOC) -o report_template.pdf $<

# convert the markdown template to latex
textemplate: ../report_template.md
	$(PANDOC) -o report_template.tex $<

# convert the markdown template to word
wordtemplate: ../report_template.md
	$(PANDOC) -o report_template.docx $<

all:  pdftemplate textemplate wordtemplate

clean:
	rm out/*
