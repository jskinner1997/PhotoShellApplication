# PhotoShellApplication

AVAILABLE COMMANDS

Application Command
        album: Return photos by album Id
        dump: Return all photos
        photo: Return by photo Id
        return: Return all photos or filter

Built-In Commands
        clear: Clear the shell screen.
        exit, quit: Exit the shell.
        help: Display help about available commands.
        script: Read and execute commands from a file.
        stacktrace: Display the full stacktrace of the last error.

Examples Shell Commands
        shell:>album 1
        shell:>photo 50
        shell:>photo --id 50
        shell:>return 2 51
        shell:>return 2 --photo 51
        shell:>return --photo 2
        shell:>return --album 80
        shell:>return --album 1 --photo 2
        shell:>return
        shell:>dump
